(ns zx.tape)

(defn to-n-bytes [num-bytes]
  (fn [n]
    (->> (range num-bytes)                       ; little endian, start from 0
         (mapv #(bit-shift-right n (* 8 %)))
         (mapv #(bit-and 0xff %)))))

(def start-address 45000)

(defn with-checksum [data]
  (conj (vec data)
        (reduce bit-xor data)))

(defn create-header [len-data name]
  (concat
    [0x03]                                            ; this means "data" block
    (mapv byte name)                                   ; name of the loaded data
    ((to-n-bytes 2) len-data)                         ; length of pure data
    ((to-n-bytes 2) start-address)                    ; address to load data
    [0x00, 0x80]))                                    ; "reserved"

(defn create-block [name data]
  {:pre [(= 10 (count name))]}                        ; must be exactly 10 chars
  (let [data-len (count data)]
    (byte-array
      (concat
        [0x13 0x00 0x00]                              ; block start
        (with-checksum (create-header data-len name)) ; header with checksum
        ((to-n-bytes 2) (+ 2 data-len))               ; length with flag + checksum
        (with-checksum (concat [0xff]                 ; flag byte
                               data))))))             ; the pure data

(comment
  ((to-n-bytes 3) 58692)
  ((to-n-bytes 3) 56129)
  ((to-n-bytes 2) 6002)
  (+ 68 (* 256 229))
  (create-block "0123456789" [0xf3 0xaf])
  :-)
