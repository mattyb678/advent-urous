(ns advent-of-code.day14
  (:require [clojure.java.io :as io]))

(defn- -parse-reindeer [s]
  (let [regex #"^([\w]+).*\s(\d+)\skm\/s.*\s(\d+)\sseconds.*\s(\d+)\sseconds"
        matches (re-find regex s)]
    (if-not (nil? matches)
      {:name (nth matches 1)
       :speed (read-string (nth matches 2))
       :fly-time (read-string (nth matches 3))
       :rest-time (read-string (nth matches 4))
       :dist 0})))

(defn- -dist-after [reindeer secs]
  (let [speed (reindeer :speed) fly-time (reindeer :fly-time)
        rest-time (reindeer :rest-time)
        num-flights (quot secs (+ fly-time rest-time))
        left-over-time (- secs (* num-flights (+ rest-time fly-time)))
        distance (+ (* speed fly-time num-flights)
                    (* (min left-over-time fly-time) speed))]
    (assoc reindeer :dist distance)))

(defn- -first-place [reindeers]
  ((apply max-key :dist reindeers) :dist))

(defn- -reward-points [reindeers]
  (let [leader-dist (-first-place reindeers)]
    (map #(if (= leader-dist (% :dist))
            (update-in % [:points] (fn [pts] (inc (or pts 0))))
            %)
         reindeers)))

(defn- -input-lines [file-name]
  (with-open [rdr (io/reader (io/file (io/resource file-name)))]
    (doall (line-seq rdr))))

(defn- -part-1 [reindeers time]
  (->> reindeers
       (map #(-dist-after % time))
       (map #(% :dist))
       (apply max)))

(defn- -part-2 [parsed-reindeers time]
  (loop [cur-time 1 reindeers parsed-reindeers]
    (if (> cur-time time)
      ((apply max-key :points (remove #(nil? (% :points)) reindeers)) :points)
      (recur (inc cur-time)
             (-reward-points (map #(-dist-after % cur-time) reindeers))))))

(defn challenge []
  (let [reindeers (map -parse-reindeer (-input-lines "day14.txt"))]
    (println "Day 14")
    (println (-part-1 reindeers 2503))
    (println (-part-2 reindeers 2503))))
