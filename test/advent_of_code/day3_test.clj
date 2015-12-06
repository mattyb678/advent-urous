(ns advent-of-code.day3-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day3 :as day3]))

(def start {:x 0 :y 0})

(deftest test-visit1
  (is (= {:x 0 :y 1} (#'day3/-visit start \^))))

(deftest test-visit2
  (is (= {:x 0 :y -1} (#'day3/-visit start \v))))

(deftest test-visit3
  (is (= {:x -1 :y 0} (#'day3/-visit start \<))))

(deftest test-visit4
  (is (= {:x 1 :y 0} (#'day3/-visit start \>))))

(deftest test1
  (is (= 2 (count (#'day3/-houses-visited ">")))))

(deftest test2
  (is (= 4 (count (#'day3/-houses-visited "^>v<")))))

(deftest test3
  (is (= 2 (count (#'day3/-houses-visited "^v^v^v^v^v")))))

(deftest test4
  (is (= 3 (count (#'day3/-houses-visited-robo "^v")))))

(deftest test5
  (is (= 3 (count (#'day3/-houses-visited-robo "^^>v<")))))

(deftest test6
  (is (= 11 (count (#'day3/-houses-visited-robo "^v^v^v^v^v")))))
