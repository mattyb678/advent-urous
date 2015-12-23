(ns advent-of-code.day14
  (:require [clojure.java.io :as io]))

(defn- -parse-line [s]
  (let [regex #"^([\w]+).*\s(\d+)\skm\/s.*\s(\d+)\sseconds.*\s(\d+)\sseconds"
        matches (re-find regex s)]
    (if-not (nil? matches)
      {:name (nth matches 1)
       :speed (read-string (nth matches 2))
       :time (read-string (nth matches 3))
       :rest-time (read-string (nth matches 4))})))

(defn- -dist-after [reindeer secs]
  (let [speed (reindeer :speed) time (reindeer :time)
        rest-time (reindeer :rest-time)]
    (loop [time-left secs dist 0 status :flying]
      (cond
       (>= 0 time-left) dist
       (= status :flying)
       (if (>= time-left time)
         (recur (- time-left time) (+ dist (* time speed)) :resting)
         (recur (- time-left time) (+ dist (* time-left speed)) :flying))
       (= status :resting) (recur (- time-left rest-time) dist :flying)))))

(defn- -input-lines [file-name]
  (with-open [rdr (io/reader (io/file (io/resource file-name)))]
    (doall (line-seq rdr))))

(defn- -part-1 [lines time]
  (->> lines
       (map #(-parse-line %))
       (map #(-dist-after % time))
       (apply max)))

(defn challenge []
  (let [lines (-input-lines "day14.txt")]
    (println "Day 14")
    (println (-part-1 lines 2503))))
