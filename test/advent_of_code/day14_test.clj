(ns advent-of-code.day14-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day14 :as day14]))

(deftest test-parse-line1
  (is (= {:name "Dancer" :speed 37 :time 1 :rest-time 36}
         (#'day14/-parse-line "Dancer can fly 37 km/s for 1 seconds, but then must rest for 36 seconds."))))

(deftest test-parse-line2
  (is (= {:name "Comet" :speed 14 :time 10 :rest-time 127}
         (#'day14/-parse-line "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds."))))

(deftest test-parse-line3
  (is (= {:name "Dancer" :speed 16 :time 11 :rest-time 162}
         (#'day14/-parse-line "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."))))

(deftest test-dist-after1
  (is (= 14
         (#'day14/-dist-after {:name "Comet" :speed 14 :time 10 :rest-time 127}
                              1))))

(deftest test-dist-after2
  (is (= 16
         (#'day14/-dist-after {:name "Dancer" :speed 16 :time 11 :rest-time 162}
                              1))))

(deftest test-dist-after3
  (is (= 140
         (#'day14/-dist-after {:name "Comet" :speed 14 :time 10 :rest-time 127}
                              10))))
(deftest test-dist-after4
  (is (= 160
         (#'day14/-dist-after {:name "Dancer" :speed 16 :time 11 :rest-time 162}
                              10))))

(deftest test-dist-after5
  (is (= 1120
         (#'day14/-dist-after {:name "Comet" :speed 14 :time 10 :rest-time 127}
                              1000))))

(deftest test-dist-after6
  (is (= 1056
         (#'day14/-dist-after {:name "Dancer" :speed 16 :time 11 :rest-time 162}
                              1000))))
