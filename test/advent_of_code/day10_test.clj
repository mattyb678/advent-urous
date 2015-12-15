(ns advent-of-code.day10-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day10 :as day10]))

(deftest test-look-say1
  (is (= 11 (#'day10/-look-say 1))))

(deftest test-look-say2
  (is (= 21 (#'day10/-look-say 11))))

(deftest test-look-say3
  (is (= 1211 (#'day10/-look-say 21))))

(deftest test-look-say4
  (is (= 111221 (#'day10/-look-say 1211))))

(deftest test-look-say5
  (is (= 312211 (#'day10/-look-say 111221))))

(deftest test-apply1
  (is (= 312211 (#'day10/-apply-times 1 5))))
