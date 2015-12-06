(ns advent-of-code.day4-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day4 :as day4]))

(deftest test1
  (is (= 609043 (#'day4/-mine "abcdef" 5))))

(deftest test2
  (is (= 1048970 (#'day4/-mine "pqrstuv" 5))))
