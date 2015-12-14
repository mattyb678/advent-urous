(ns advent-of-code.day8-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day8 :as day8]))

(deftest test-replace-ascii1
  (is (= "'" (#'day8/-replace-ascii "\\x27"))))

(deftest test-replace-ascii2
  (is (= "hello" (#'day8/-replace-ascii "hello"))))

(deftest test-replace-ascii3
  (is (= "'blah!" (#'day8/-replace-ascii "\\x27blah\\x21"))))

(deftest test-replace-escape1
  (is (= "" (#'day8/-replace-escape "\"\""))))

(deftest test-replace-escape2
  (is (= "abc" (#'day8/-replace-escape "\"abc\""))))

(deftest test-replace-escape3
  (is (= "\\" (#'day8/-replace-escape "\"\\\\\""))))

(deftest test-replace-escape4
  (is (= "nywbv\\" (#'day8/-replace-escape "\"nywbv\\\\\""))))

(deftest test-diff1
  (is (= 2 (#'day8/-diff-pt1 "\"\""))))

(deftest test-diff2
  (is (= 2 (#'day8/-diff-pt1 "\"abc\""))))

(deftest test-diff3
  (is (= 3 (#'day8/-diff-pt1 "\"aaa\\\"aaa\""))))

(deftest test-diff4
  (is (= 5 (#'day8/-diff-pt1 "\"\\x27\""))))

(deftest test-diff5
  (is (= 3 (#'day8/-diff-pt1 "\"\\\\\""))))

(deftest test-diff6
  (is (= 2 (#'day8/-diff-pt1 "\"\"\""))))

(deftest test-diff7
  (is (= 3 (#'day8/-diff-pt1 "\"nywbv\\\\\""))))

(deftest test-encode1
  (is (= "\"\\\"\\\"\"" (#'day8/-encode "\"\""))))

(deftest test-encode-count1
  (is (= 6 (count (#'day8/-encode "\"\"")))))

(deftest test-encode2
  (is (= "\"\\\"abc\\\"\"" (#'day8/-encode "\"abc\""))))

(deftest test-encode-count2
  (is (= 9 (count (#'day8/-encode "\"abc\"")))))

(deftest test-encode3
  (is (= "\"\\\"aaa\\\\\\\"aaa\\\"\"" (#'day8/-encode "\"aaa\\\"aaa\""))))

(deftest test-encode-count3
  (is (= 16 (count (#'day8/-encode "\"aaa\\\"aaa\"")))))

(deftest test-encode4
  (is (= "\"\\\"\\\\x27\\\"\"" (#'day8/-encode "\"\\x27\""))))

(deftest test-encode-count4
  (is (= 11 (count (#'day8/-encode "\"\\x27\"")))))
