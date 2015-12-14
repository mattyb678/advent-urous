(ns advent-of-code.day9-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day9 :as day9]))

(deftest test-parse-distance1
  (is (= {:cities ["Dublin" "London"] :dist 464}
         (#'day9/-parse-distance "London to Dublin = 464"))))

(deftest test-parse-distance2
  (is (= {:cities ["Belfast" "London"] :dist 518}
         (#'day9/-parse-distance "London to Belfast = 518"))))

(deftest test-parse-distance3
  (is (= {:cities ["Belfast" "Dublin"] :dist 141}
         (#'day9/-parse-distance "Dublin to Belfast = 141"))))

(deftest test-parse-distances1
  (is (= [{:cities ["Dublin" "London"] :dist 464}
          {:cities ["Belfast" "London"] :dist 518}
          {:cities ["Belfast" "Dublin"] :dist 141}]
         (#'day9/-parse-distances ["London to Dublin = 464"
                                   "London to Belfast = 518"
                                   "Dublin to Belfast = 141"]))))
(deftest test-cities1
  (is (= #{"Belfast" "London" "Dublin"}
         (#'day9/-cities [{:cities ["Dublin" "London"] :dist 464}
                          {:cities ["Belfast" "London"] :dist 518}
                          {:cities ["Belfast" "Dublin"] :dist 141}]))))

(deftest test-lookup-distance1
  (is (= 464
         (#'day9/-lookup-distance [{:cities ["Dublin" "London"] :dist 464}
                                   {:cities ["Belfast" "London"] :dist 518}
                                   {:cities ["Belfast" "Dublin"] :dist 141}]
                                  ["Dublin" "London"]))))

(deftest test-lookup-distance2
  (is (= 141
         (#'day9/-lookup-distance [{:cities ["Dublin" "London"] :dist 464}
                                   {:cities ["Belfast" "London"] :dist 518}
                                   {:cities ["Belfast" "Dublin"] :dist 141}]
                                  ["Belfast" "Dublin"]))))

(deftest test-lookup-distance3
  (is (= 518
         (#'day9/-lookup-distance [{:cities ["Dublin" "London"] :dist 464}
                                   {:cities ["Belfast" "London"] :dist 518}
                                   {:cities ["Belfast" "Dublin"] :dist 141}]
                                  ["Belfast" "London"]))))
(deftest test-min-distance1
  (is (= 605
         (let [distances (#'day9/-parse-distances ["London to Dublin = 464"
                                                   "London to Belfast = 518"
                                                   "Dublin to Belfast = 141"])]
           (#'day9/-min-distance distances)))))

(deftest test-max-distance1
  (is (= 982
         (let [distances (#'day9/-parse-distances ["London to Dublin = 464"
                                                   "London to Belfast = 518"
                                                   "Dublin to Belfast = 141"])]
           (#'day9/-max-distance distances)))))
