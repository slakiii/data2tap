(ns zx.tape-test
  (:require
    [zx.tape :as sut]
    [clojure.test :refer [deftest is]]))

(deftest with-checksum-test
  (is (= [1 2 3]
         (sut/with-checksum [1 2]))))
