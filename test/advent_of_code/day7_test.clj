(ns advent-of-code.day7-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day7 :as day7]))

(deftest test-parse-instruction1
  (is (= ["123" "->" "x"]
         (#'day7/-parse-instruction "123 -> x"))))

(deftest test-parse-instruction2
  (is (= ["f" "AND" "g" "->" "x"]
         (#'day7/-parse-instruction "f AND g -> x"))))

(deftest test-parse-instruction3
  (is (= ["NOT" "g" "->" "x"]
         (#'day7/-parse-instruction "NOT g -> x"))))

(deftest test-parse-instruction4
  (is (= ["w" "LSHIFT" "2" "->" "x"]
         (#'day7/-parse-instruction "w LSHIFT 2 -> x"))))

(deftest test-parse-instructions1
  (is (= {"x" {:val  "123"}}
         (#'day7/-parse-instructions ["123 -> x"]))))

(deftest test-parse-instructions2
  (is (= {"x" {:val  "123"} "h" {:op bit-not :operand1 "x"}}
         (#'day7/-parse-instructions ["123 -> x" "NOT x -> h"]))))

(deftest test-parse-instructions3
  (is (= {"x" {:val  "123"} "h" {:op bit-not :operand1 "x"}
          "y" {:val "456"} "i" {:op bit-not :operand1 "y"}
          "g" {:op unsigned-bit-shift-right :operand1 "y" :operand2 "2"}
          "d" {:op bit-and :operand1 "x" :operand2 "y"}
          "f" {:op bit-shift-left :operand1 "x" :operand2 "2"}
          "e" {:op bit-or :operand1 "x" :operand2 "y"}}
         (#'day7/-parse-instructions ["123 -> x" "NOT x -> h"
                                      "y RSHIFT 2 -> g" "x AND y -> d"
                                      "456 -> y" "x OR y -> e"
                                      "x LSHIFT 2 -> f" "NOT y -> i"]))))

(deftest test-eval-var1
  (is (= 123
         (#'day7/-eval-var (#'day7/-parse-instructions ["123 -> x"]) "x"))))

(deftest test-eval-var2
  (is (= 65412
         (#'day7/-eval-var (#'day7/-parse-instructions ["123 -> x" "NOT x -> h"]) "h"))))

(deftest test-eval-var3
  (let [ctx (#'day7/-parse-instructions ["123 -> x" "NOT x -> h"
                                      "y RSHIFT 2 -> g" "x AND y -> d"
                                      "456 -> y" "x OR y -> e"
                                      "x LSHIFT 2 -> f" "NOT y -> i"])]
    (is (= 72 (#'day7/-eval-var ctx "d")))
    (is (= 507 (#'day7/-eval-var ctx "e")))
    (is (= 492 (#'day7/-eval-var ctx "f")))
    (is (= 114 (#'day7/-eval-var ctx "g")))
    (is (= 65412 (#'day7/-eval-var ctx "h")))
    (is (= 65079 (#'day7/-eval-var ctx "i")))
    (is (= 123 (#'day7/-eval-var ctx "x")))
    (is (= 456 (#'day7/-eval-var ctx "y")))))
