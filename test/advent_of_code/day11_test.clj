(ns advent-of-code.day11-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day11 :as day11]))

(deftest test-next1
  (is (= "abcdffaa" (#'day11/-next-password "abcdefgh"))))

(deftest test-next2
  (is (= "ghjaabcc" (#'day11/-next-password "ghijklmn"))))

(deftest test-inc1
  (is (= "xy" (#'day11/-inc-password "xx"))))

(deftest test-inc2
  (is (= "xz" (#'day11/-inc-password "xy"))))

(deftest test-inc3
  (is (= "ya" (#'day11/-inc-password "xz"))))

(deftest test-inc4
  (is (= "yb" (#'day11/-inc-password "ya"))))

(deftest test-has-straight1
  (is (= true (#'day11/-has-straight "abc"))))

(deftest test-has-straight2
  (is (= true (#'day11/-has-straight "asfwermnofasdf"))))

(deftest test-has-straight3
  (is (= false (#'day11/-has-straight "booyah"))))

(deftest test-contains-chars1
  (is (= true (#'day11/-contains-chars "hello" day11/invalid-chars))))

(deftest test-contains-chars2
  (is (= false (#'day11/-contains-chars "matt" day11/invalid-chars))))

(deftest test-has-doubles1
  (is (= true (#'day11/-has-doubles "abbceffg"))))

(deftest test-has-doubles2
  (is (= false (#'day11/-has-doubles "matt"))))
