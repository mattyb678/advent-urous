(ns advent-of-code.day14-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day14 :as day14]))

(deftest test-parse-reindeer1
  (is (= {:name "Dancer" :speed 37 :fly-time 1 :rest-time 36 :dist 0}
         (#'day14/-parse-reindeer "Dancer can fly 37 km/s for 1 seconds, but then must rest for 36 seconds."))))

(deftest test-parse-reindeer2
  (is (= {:name "Comet" :speed 14 :fly-time 10 :rest-time 127 :dist 0}
         (#'day14/-parse-reindeer "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds."))))

(deftest test-parse-reindeer3
  (is (= {:name "Dancer" :speed 16 :fly-time 11 :rest-time 162 :dist 0}
         (#'day14/-parse-reindeer "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."))))

(deftest test-dist-after1
  (is (= {:name "Comet" :speed 14 :fly-time 10 :rest-time 127 :dist 14}
         (#'day14/-dist-after {:name "Comet" :speed 14 :fly-time 10 :rest-time 127 :dist 0}
                              1))))

(deftest test-dist-after2
  (is (= {:name "Dancer" :speed 16 :fly-time 11 :rest-time 162 :dist 16}
         (#'day14/-dist-after {:name "Dancer" :speed 16 :fly-time 11 :rest-time 162 :dist 0}
                              1))))

(deftest test-dist-after3
  (is (= {:name "Comet" :speed 14 :fly-time 10 :rest-time 127 :dist 140}
         (#'day14/-dist-after {:name "Comet" :speed 14 :fly-time 10 :rest-time 127 :dist 0}
                              10))))
(deftest test-dist-after4
  (is (= {:name "Dancer" :speed 16 :fly-time 11 :rest-time 162 :dist 160}
         (#'day14/-dist-after {:name "Dancer" :speed 16 :fly-time 11 :rest-time 162 :dist 0}
                              10))))

(deftest test-dist-after5
  (is (= {:name "Comet" :speed 14 :fly-time 10 :rest-time 127 :dist 1120}
         (#'day14/-dist-after {:name "Comet" :speed 14 :fly-time 10 :rest-time 127 :dist 0}
                              1000))))

(deftest test-dist-after6
  (is (= {:name "Dancer" :speed 16 :fly-time 11 :rest-time 162 :dist 1056}
         (#'day14/-dist-after {:name "Dancer" :speed 16 :fly-time 11 :rest-time 162 :dist 0}
                              1000))))

(deftest test-first-place1
  (is (= 160
         (#'day14/-first-place [{:name "Dancer" :speed 16 :fly-time 11 :rest-time 162 :dist 160}
                                {:name "Comet" :speed 14 :fly-time 10 :rest-time 127 :dist 140}]))))

(deftest test-part2-1
  (is (= 1
         (#'day14/-part-2 [{:name "Dancer" :speed 16 :fly-time 11 :rest-time 162 :dist 0}
                           {:name "Comet" :speed 14 :fly-time 10 :rest-time 127 :dist 0}]
                          1))))
