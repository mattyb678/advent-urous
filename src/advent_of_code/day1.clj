(ns advent-of-code.day1
  (:require [clojure.java.io :as io]))

(def input-str (slurp (io/file (io/resource "not-quite-lisp.txt"))))

(defn -count-occur [full-str paren]
  (count (re-seq (re-pattern (str "\\" paren)) full-str)))

(defn -directions [dir-str]
  (let [num-left (-count-occur dir-str "(")
        num-right (-count-occur dir-str ")")]
    (- (+ num-left 0) num-right)))

(defn -position-at-basement
  [pos coll floor]
  (if (< floor 0)
    pos
    (if (empty? coll)
      :never-went-to-basement
      (recur (inc pos)
             (rest coll)
             (if (= \( (first coll))
               (inc floor)
               (dec floor))))))

(defn challenge []
  (println "Day 1")
  (println "Floor: " (-directions input-str))
  (println "Position: " (-position-at-basement 0 input-str 0)))

