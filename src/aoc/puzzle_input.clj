(ns aoc.puzzle-input
  (:require
    [clojure.java.io :as io]
    [zx.tape :as tape]))

(defn process-line [line]
  (->> line
       (re-seq #"\d+")
       (mapv parse-long)
       (mapv (tape/to-n-bytes 3))
       flatten))

(defn write-tap [lines file-name]
  (let [data (transduce
               (map process-line)
               concat
               lines)]
    (with-open [w (io/output-stream file-name)]
      (.write w (tape/create-block data)))))

(defn file-to-lines [file-path]
  (with-open [rdr (io/reader file-path)]
    (reduce conj [] (line-seq rdr))))

(comment
  (process-line "256 2")
  :-)
