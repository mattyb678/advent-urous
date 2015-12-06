(ns advent-of-code.day3
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input-str (str/trim (slurp (io/file (io/resource "day3.txt")))))

(def start-loc {:x 0 :y 0})

(defn- -visit [cur-loc dir]
  (let [x (cur-loc :x)
        y (cur-loc :y)]
    (case dir
      \^ (assoc cur-loc :y (inc y))
      \v (assoc cur-loc :y (dec y))
      \< (assoc cur-loc :x (dec x))
      \> (assoc cur-loc :x (inc x))
      cur-loc)))

(defn- -houses-visited [nav-str]
  (let [houses-set (set [start-loc])]
    (loop [houses houses-set
           loc-str nav-str
           cur-loc start-loc]
      (if (empty? loc-str)
        houses
        (let [new-loc (-visit cur-loc (first loc-str))]
          (recur (conj houses new-loc) (rest loc-str) new-loc))))))

(defn- -houses-visited-robo [nav-str]
  (let [santa-set (set [start-loc])
        robo-set (set [start-loc])]
    (loop [idx 0 santa-houses santa-set
           robo-houses robo-set loc-str nav-str
           santa-loc start-loc robo-loc start-loc]
      (if (empty? loc-str)
        (into (set []) (concat santa-houses robo-houses))
        (if (= 0 (mod idx 2))
          (let [new-santa-loc (-visit santa-loc (first loc-str))]
            (recur (inc idx) (conj santa-houses new-santa-loc) robo-houses
                   (rest loc-str) new-santa-loc robo-loc))
          (let [new-robo-loc (-visit robo-loc (first loc-str))]
            (recur (inc idx) santa-houses (conj robo-houses new-robo-loc)
                   (rest loc-str) santa-loc new-robo-loc)))))))

(defn challenge []
  (println "Day 3")
  (println "Houses Visited: " (count (-houses-visited input-str)))
  (println "Houses Visited (Robo-Santa):" (count (-houses-visited-robo input-str))))
