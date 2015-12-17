(ns advent-of-code.day13-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day13 :as day13]))

(deftest test-extract1
  (is (= {["Alice" "Bob"] 54}
         (#'day13/-parse-line "Alice would gain 54 happiness units by sitting next to Bob."))))

(deftest test-extract2
  (is (= {["Alice" "Bob"] 83}
         (#'day13/-parse-line "Bob would gain 83 happiness units by sitting next to Alice."))))

(deftest test-extract3
  (is (= {["Bob" "David"] -7}
         (#'day13/-parse-line "David would lose 7 happiness units by sitting next to Bob."))))

(deftest test-parse-context1
  (is (= {["Bob" "David"] -7}
         (#'day13/-parse-context ["David would lose 7 happiness units by sitting next to Bob."]))))

(deftest test-parse-context2
  (is (= {["Bob" "David"] -7
          ["Alice" "Bob"] 137}
         (#'day13/-parse-context
          ["Alice would gain 54 happiness units by sitting next to Bob."
           "David would lose 7 happiness units by sitting next to Bob."
           "Bob would gain 83 happiness units by sitting next to Alice."]))))

(deftest test-parse-context-full
  (is (= {["Alice" "Bob"] 137
          ["Alice" "Carol"] -141
          ["Alice" "David"] 44
          ["Bob" "Carol"] 53
          ["Bob" "David"] -70
          ["Carol" "David"] 96}
         (#'day13/-parse-context
          ["Alice would gain 54 happiness units by sitting next to Bob." 
           "Alice would lose 79 happiness units by sitting next to Carol."
           "Alice would lose 2 happiness units by sitting next to David."
           "Bob would gain 83 happiness units by sitting next to Alice."
           "Bob would lose 7 happiness units by sitting next to Carol."
           "Bob would lose 63 happiness units by sitting next to David."
           "Carol would lose 62 happiness units by sitting next to Alice."
           "Carol would gain 60 happiness units by sitting next to Bob."
           "Carol would gain 55 happiness units by sitting next to David."
           "David would gain 46 happiness units by sitting next to Alice."
           "David would lose 7 happiness units by sitting next to Bob."
           "David would gain 41 happiness units by sitting next to Carol."]))))

(deftest test-attendees1
  (is (= ["Alice" "Bob" "David"]
         (#'day13/-attendees {["Bob" "David"] -7
                              ["Alice" "Bob"] 137}))))

(deftest test-max-score-full
  (is (= 330
         (#'day13/-max-score {["Alice" "Bob"] 137
                              ["Alice" "Carol"] -141
                              ["Alice" "David"] 44
                              ["Bob" "Carol"] 53
                              ["Bob" "David"] -70
                              ["Carol" "David"] 96}))))

