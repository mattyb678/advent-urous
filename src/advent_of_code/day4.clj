(ns advent-of-code.day4
  (:require [digest]))

(def puzzle-input "yzbqklnj")

(defn- -mine [secret-key num]
  (let [cmp-to (apply str (repeat num "0"))]
    (loop [ans 1]
      (let [hash (digest/md5 (str secret-key ans))]
        (if (= cmp-to (subs hash 0 num))
          ans
          (recur (inc ans)))))))

(defn challenge []
  (println "Day 4")
  (println "Starts with 5: " (-mine puzzle-input 5))
  (println "Starts with 6: " (-mine puzzle-input 6)))
