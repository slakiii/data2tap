(ns zx.tape-test
  (:require
    [zx.tape :as sut]
    [clojure.test :refer [deftest is]]))

(deftest load-name-test
  (is (= 10 (count sut/load-name))))

(deftest with-checksum-test
  (is (= [1 2 3]
         (sut/with-checksum [1 2]))))
