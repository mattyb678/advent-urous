(ns advent-of-code.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn- -calc-wrapping-needs [l w h]
  (let [min-area (min (* l h) (* h w) (* l w))]
    (+ (* 2 l h) (* 2 l w) (* 2 w h) min-area)))

(defn- -calc-ribbon-needs [l w h]
  (let [biggest-side (max l w h)
        smallest (take 2 (sort [l w h]))
        present-vol (* l w h)]
    (+ present-vol (reduce + (map #(* 2 %) smallest)))))

(defn- -extract-lengths [sides-str]
  (map read-string (str/split sides-str #"x")))

(defn- -calc-wrapping-line [file-name]
  (with-open [rdr (io/reader (io/file (io/resource file-name)))]
    (doall  (map #(-calc-wrapping-needs (nth % 0) (nth % 1) (nth % 2))
                 (map -extract-lengths (line-seq rdr))))))

(defn- -calc-ribbon-line [file-name]
  (with-open [rdr (io/reader (io/file (io/resource file-name)))]
    (doall (map #(-calc-ribbon-needs (nth % 0) (nth % 1) (nth % 2))
                (map -extract-lengths (line-seq rdr))))))

(defn challenge []
  (println "Day 2")
  (println "Total Wrapping needs: " (reduce + (-calc-wrapping-line "no-math.txt")))
  (println "Total Ribbon needs: " (reduce + (-calc-ribbon-line "no-math.txt"))))
