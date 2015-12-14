(ns advent-of-code.day9
  (:require [clojure.java.io :as io]
            [clojure.math.combinatorics :as combo]))

(defn- -parse-distance [s]
  (let [regex #"(.*)\sto\s(.*)\s=\s(\d+)"
        match (re-find regex s)]
    {:cities (sort (list (nth match 1) (nth match 2)))
     :dist (read-string (nth match 3))}))

(defn- -parse-distances [strings]
  (map #(-parse-distance %) strings))

(defn- -cities [distances]
  (into #{}
        (sort
         (flatten
          (reduce #(-> %1
                       (conj (%2 :cities))) [] distances)))))

(def -lookup-distance
  (memoize
   (fn [distances two-cities]
     ((first (filter #(= (% :cities) (sort two-cities)) distances)) :dist))))

(defn- -lookup-distances [distances perm]
  (->> (partition 2 1 perm)
       (map #(-lookup-distance distances %))
       (reduce +)))

(def -calc-distances
  (memoize
   (fn [distances]
     (let [cities (-cities distances)
           perms (combo/permutations cities)]
       (map #(-lookup-distances distances %) perms)))))

(defn- -min-distance [distances]
  (let [calced-distances (-calc-distances distances)]
    (apply min calced-distances)))

(defn- -max-distance [distances]
  (let [calced-distances (-calc-distances distances)]
    (apply max calced-distances)))

(defn- -process-file [file-name]
  (with-open [rdr (io/reader (io/file (io/resource file-name)))]
    (doall (line-seq rdr))))

(defn challenge []
  (let [strings (-process-file "day9.txt")
        distances (-parse-distances strings)]
    (println "Day 9")
    (println (-min-distance distances))
    (println (-max-distance distances))))
