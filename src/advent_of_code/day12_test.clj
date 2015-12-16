(ns advent-of-code.day12-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day12 :as day12]))

(deftest test-sum1
  (is (= 6 (#'day12/-part1 "[1,2,3]"))))

(deftest test-sum2
  (is (= 6 (#'day12/-part1 "{\"a\":2,\"b\":4}"))))

(deftest test-sum3
  (is (= 3 (#'day12/-part1 "[[[3]]]"))))

(deftest test-sum4
  (is (= 3 (#'day12/-part1 "{\"a\":{\"b\":4},\"c\":-1}"))))

(deftest test-sum5
  (is (= 0 (#'day12/-part1 "{\"a\":[-1,1]}"))))

(deftest test-sum6
  (is (= 0 (#'day12/-part1 "[-1,{\"a\":1}]"))))

(deftest test-sum7
  (is (= 0 (#'day12/-part1 "[]"))))

(deftest test-sum8
  (is (= 0 (#'day12/-part1 "{}"))))

(deftest test-part21
  (is (= 6 (#'day12/-part2 "[1,2,3]"))))

(deftest test-part22
  (is (= 4 (#'day12/-part2 "[1,{\"c\":\"red\",\"b\":2},3]"))))

(deftest test-part23
  (is (= 0 (#'day12/-part2 "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}"))))

(deftest test-part24
  (is (= 6 (#'day12/-part2 "[1,\"red\",5]"))))
