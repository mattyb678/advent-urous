(ns advent-of-code.day12
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(defn- -sum [jsn-str should-visit?]
  (let [walk (fn walk [node]
               (if (number? node)
                 (cons node [])
                 (when (should-visit? node)
                   (mapcat walk node))
                 ))
        jsn (json/read-str jsn-str)]
    (->> (walk jsn)
         (reduce + 0))))

(defn- -part1 [jsn-str]
  (-sum jsn-str coll?))

(defn- -part2 [jsn-str]
  (-sum jsn-str #(if (map? %)
                   (nil? (some (fn [val] (= val "red")) (vals %)))
                   (coll? %))))

(defn- -input-jsn [file-name]
  (slurp (io/file (io/resource file-name))))

(defn challenge []
  (println "Day 12")
  (println (-part1 (-input-jsn "day12.txt")))
  (println (-part2 (-input-jsn "day12.txt"))))
