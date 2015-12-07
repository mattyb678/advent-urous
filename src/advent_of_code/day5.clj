(ns advent-of-code.day5
  (:require [clojure.java.io :as io]))

(def vowels-regex #"(.*[aeiou].*){3}")
(def double-regex #".*(.)\1.*")
(def disallowed-regex #".*(ab|cd|pq|xy).*")

(defn- -categorize-pt1 [to-check]
  (if (not (nil? (re-matches vowels-regex to-check)))
    (if (not (nil? (re-matches double-regex to-check)))
      (if (nil? (re-matches disallowed-regex to-check))
        :nice
        :naughty)
      :naughty)
    :naughty))

(def letter-in-between-regex #".*(.).{1}\1.*")
(def double-letter-regex #".*(.{2}).*\1.*")

(defn- -categorize-pt2 [to-check]
  (if (not (nil? (re-matches letter-in-between-regex to-check)))
    (if (not (nil? (re-matches double-letter-regex to-check)))
      :nice
      :naughty)
    :naughty))

(defn- -categorize-lines [file-name fnc]
  (with-open [rdr (io/reader (io/file (io/resource file-name)))]
    (doall (map fnc (line-seq rdr)))))

(defn- -count-nice [fnc file-name]
  (count (filter #(= :nice %) (-categorize-lines file-name fnc))))

(defn challenge []
  (println "Day 5")
  (println "# nice: " (-count-nice -categorize-pt1 "day5.txt"))
  (println "# nice (pt2): " (-count-nice -categorize-pt2 "day5.txt")))
