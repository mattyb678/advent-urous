(ns advent-of-code.day5-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day5 :as day5]))

(deftest test1
  (is (= :nice (#'day5/-categorize-pt1 "ugknbfddgicrmopn"))))

(deftest test2
  (is (= :nice (#'day5/-categorize-pt1 "aaa"))))

(deftest test3
  (is (= :naughty (#'day5/-categorize-pt1 "jchzalrnumimnmhp"))))

(deftest test4
  (is (= :naughty (#'day5/-categorize-pt1 "haegwjzuvuyypxyu"))))

(deftest test5
  (is (= :naughty (#'day5/-categorize-pt1 "dvszwmarrgswjxmb"))))

(deftest test6
  (is (= :nice (#'day5/-categorize-pt2 "qjhvhtzxzqqjkmpb"))))

(deftest test7
  (is (= :nice (#'day5/-categorize-pt2 "xxyxx"))))

(deftest test8
  (is (= :naughty (#'day5/-categorize-pt2 "uurcxstgmygtbstg"))))

(deftest test9
  (is (= :naughty (#'day5/-categorize-pt2 "ieodomkazucvgmuy"))))
