(ns advent-of-code.day6-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day6 :as day6]))

(deftest test-parse-instruction1
  (is (= {:action :turn-on :start {:x 0 :y 0} :end {:x 999 :y 999}}
         (#'day6/-parse-instruction "turn on 0,0 through 999,999"))))

(deftest test-parse-instruction2
  (is (= {:action :turn-off :start {:x 10 :y 101} :end {:x 99 :y 919}}
         (#'day6/-parse-instruction "turn off 10,101 through 99,919"))))

(deftest test-parse-instruction3
  (is (= {:action :toggle :start {:x 50 :y 11} :end {:x 9 :y 19}}
         (#'day6/-parse-instruction "toggle 50,11 through 9,19"))))

(deftest test-init
  (is (= [[0]]
         (#'day6/-init 1 1))))

(deftest test-init2
  (is (= [[0 0]]
         (#'day6/-init 2 1))))

(deftest test-init3
  (is (= [[0] [0]]
         (#'day6/-init 1 2))))

(deftest test-init4
  (is (= [[0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0]]
         (#'day6/-init 10 10))))

(deftest test-init5
  (is (= 100 (count (#'day6/-init 1 100)))))

(deftest test-init6
  (is (= 1 (count (#'day6/-init 100 1)))))

(deftest test-init7
  (is (= 15 (count (nth (#'day6/-init 15 22) 5)))))

(deftest test-update-row1
  (is (= [0 0 0]
         (#'day6/-update-row [0 0 0] :turn-off 0 2))))

(deftest test-update-row1-part2
  (is (= [0 0 0]
         (#'day6/-update-row [0 0 0] :turn-off 0 2 :part2))))

(deftest test-update-row2
  (is (= [1 1 1]
         (#'day6/-update-row [0 0 0] :turn-on 0 2))))

(deftest test-update-row2-part2
  (is (= [1 1 1]
         (#'day6/-update-row [0 0 0] :turn-on 0 2 :part2))))

(deftest test-update-row3
  (is (= [1 0 1]
         (#'day6/-update-row [0 1 0] :toggle 0 2))))

(deftest test-update-row3-part2
  (is (= [0 2 0]
         (#'day6/-update-row [0 1 0] :toggle 0 2 :part2))))

(deftest test-update-row4
  (is (= [1 1 1 1 1 0 0 0 0 0]
         (#'day6/-update-row [0 0 0 0 0 0 0 0 0 0] :turn-on 0 4))))

(deftest test-update-row5
  (is (= [0 0 0 1 1 1 1 0 0 0]
         (#'day6/-update-row [0 0 0 0 0 0 0 0 0 0] :turn-on 3 6))))

(deftest test-update-row6
  (is (= [1 0 1 0 1 0 1]
         (#'day6/-update-row [0 1 0 1 0 1 0] :toggle 0 6))))

(deftest test-update-rows1
  (is (= [[1] [1]]
         (#'day6/-update-rows [[0] [0]] :turn-on 0 1 0 0 ))))

(deftest test-update-rows1
  (is (= [[0 0 0 0] [0 0 0 0] [1 1 1 1]]
         (#'day6/-update-rows [[1 1 1 1] [1 1 1 1][1 1 1 1]]
                              :turn-off 0 1 0 3 ))))

(deftest test-update-rows1
  (is (= [[0 0 0 0] [0 0 0 0] [1 1 1 1]]
         (#'day6/-update-rows [[1 1 1 1] [1 1 1 1][0 0 0 0]]
                              :toggle 0 2 0 3 ))))

(deftest test-apply1
  (is (= [[1 1 1] [1 1 1] [1 1 1]]
         (#'day6/-apply-instruction {:action :turn-on
                                     :start {:x 0 :y 0}
                                     :end {:x 2 :y 2}}
                                    (#'day6/-init 3 3)))))

(deftest test-apply2
  (is (= [[1 1 1] [1 1 1] [0 0 0]]
         (#'day6/-apply-instruction {:action :turn-off
                                     :start {:x 0 :y 2}
                                     :end {:x 2 :y 2}}
                                    (#'day6/-apply-instruction {:action :turn-on
                                                                :start {:x 0 :y 0}
                                                                :end {:x 2 :y 2}}
                                                               (#'day6/-init 3 3))))))
