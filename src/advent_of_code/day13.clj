(ns advent-of-code.day13
  (:require [clojure.math.combinatorics :as combo]
            [clojure.java.io :as io]))

(defn- -parse-line [s]
  (let [regex #"^([\w]+)\s.*(gain|lose)\s(\d+)\s.*\s([\w]+)\.$"
        matches (re-find regex s)]
    {(sort [(nth matches 1) (nth matches 4)])
     (* (read-string (nth matches 3)) (if (= (nth matches 2) "gain") 1 -1))}))

(def -attendees
  (memoize
   (fn [ctx]                
     (->> ctx
          (keys)
          (flatten)
          (distinct)))))

(defn- -parse-context [ses]
  (let [parsed-lines (map #(-parse-line %) ses)]
    (apply merge-with + parsed-lines)))

(def get-score
  (memoize
   (fn [ctx peeps]
     (ctx peeps))))

(defn- -calc-score [perm ctx]
  (let [other-pair [(ffirst perm) (last (last perm))]]
    (->> other-pair
         (conj perm)
         (map #(get-score ctx (sort %)))
         (reduce +))))

(defn- -calc-scores [ctx]
  (let [attendees (-attendees ctx)
        perms (map #(partition 2 1 %) (combo/permutations attendees))]
    (map #(-calc-score % ctx) perms)))

(defn- -max-score [ctx]
  (apply max (-calc-scores ctx)))

(defn- -input-lines [file-name]
  (with-open [rdr (io/reader (io/file (io/resource file-name)))]
    (doall (line-seq rdr))))

(defn- -add-myself [ctx]
  (let [attendees (-attendees ctx)]
    (merge ctx (apply merge (map #(hash-map (sort ["Me" %]) 0) attendees)))))

(defn challenge []
  (let [lines (-input-lines "day13.txt")
        ctx (-parse-context lines)
        new-ctx (-add-myself ctx)]
    (println "Day 13")
    (println (-max-score ctx))
    (println (-max-score new-ctx))))
