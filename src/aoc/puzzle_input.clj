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

(defn process-all-lines [lines]
  (transduce
    (map process-line)
    concat
    lines))

(defn write-to-file [file-name data]
  (with-open [w (io/output-stream file-name)]
    (.write w data)))

(defn file-to-lines [file-path]
  (with-open [rdr (io/reader file-path)]
    (reduce conj [] (line-seq rdr))))

(defn -main [& args]
  (let [[input-file output-file] args]
    (if output-file
      (->> (file-to-lines input-file)
           (process-all-lines)
           (tape/create-block)
           (write-to-file output-file))
      (println "Next time call this with 2 file names, an input file and an output file."))))

(comment
  (process-line "256 2")
  :-)
