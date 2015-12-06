(ns advent-of-code.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day1 :as day1]))

(deftest test1
  (testing "0"
    (is (= 0 (#'day1/-directions "(())")))))

(deftest test2
  (testing "0"
    (is (= 0 (#'day1/-directions "()()")))))

(deftest test3
  (testing "3"
    (is (= 3 (#'day1/-directions "(((")))))

(deftest test4
  (testing "3"
    (is (= 3 (#'day1/-directions "(()(()(")))))

(deftest test5
  (testing "3"
    (is (= 3 (#'day1/-directions "))(((((")))))

(deftest test6
  (testing "-1"
    (is (= -1 (#'day1/-directions "())")))))

(deftest test7
  (testing "-1"
    (is (= -1 (#'day1/-directions "))(")))))

(deftest test8
  (testing "-3"
    (is (= -3 (#'day1/-directions ")))")))))

(deftest test9
  (testing "-3"
    (is (= -3 (#'day1/-directions ")())())")))))

(deftest test10
  (testing "pos 1"
    (is (= 1 (#'day1/-position-at-basement 0 ")" 0)))))

(deftest test11
  (testing "pos 5"
    (is (= 5 (#'day1/-position-at-basement 0 "()())" 0)))))
