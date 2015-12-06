(ns advent-of-code.day2-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day2 :as day2]))

(deftest test1
  (testing "58"
    (is (= 58 (#'day2/-calc-wrapping-needs 2 3 4)))))

(deftest test1.1
  (testing "2x3x4"
    (is (= [2 3 4] (#'day2/-extract-lengths "2x3x4")))))

(deftest test2
  (testing "43"
    (is (= 43 (#'day2/-calc-wrapping-needs 1 1 10)))))

(deftest test2.1
  (testing "1x1x10"
    (is (= [1 1 10] (#'day2/-extract-lengths "1x1x10")))))

(deftest test3
  (testing "ribbon 34"
    (is (= 34 (#'day2/-calc-ribbon-needs 2 3 4)))))

(deftest test4
  (testing "ribbon 14"
    (is (= 14 (#'day2/-calc-ribbon-needs 1 1 10)))))
