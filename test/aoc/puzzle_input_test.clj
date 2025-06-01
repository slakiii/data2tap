(ns aoc.puzzle-input-test
  (:require
    [aoc.puzzle-input :as sut]
    [clojure.test :refer [deftest is]]))

(deftest process-line-test
  (is (= '(0 1 0 2 0 0)
         (sut/process-line "256 2"))))
