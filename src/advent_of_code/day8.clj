(ns advent-of-code.day8
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn- -replace-ascii [s]
  (let [matches (re-seq #"\\x([a-f0-9]{2})" s)
        num-matches (count matches)]
    (if (nil? matches)
      s
      (loop [x 0 new-str s]
        (if (>= x num-matches)
          new-str
          (recur (inc x)
                 (str/replace new-str
                              (str "\\x" (nth (nth matches x) 1))
                              (str (char (read-string (str "0x" (nth (nth matches x) 1))))))))))))

(defn- -replace-escape [s]
  (-> s
      (str/replace #"(^\"|\"$)" "")
      (str/replace #"\\\"" "\"")
      (str/replace #"\\\\" "\\\\")
      (-replace-ascii)))

(defn- -encode [s]
  (str \" (-> s
              (str/replace #"\\" "\\\\\\\\")
              (str/replace #"\"" "\\\\\"")
              )
       \"))

(defn- -diff-pt2 [s]
  (- (count (-encode s)) (count s)))

(defn- -diff-pt1 [s]
  (let [replaced (-replace-escape s)]
    (- (count s) (count replaced))))

(defn- -process-file [file-name]
  (with-open [rdr (io/reader (io/file (io/resource file-name)))]
    (doall (line-seq rdr))))

(defn challenge []
  (let [strings (-process-file "day8.txt")]
    (println "Day 8")
    (println  (reduce + (map #(-diff-pt1 %) strings)))
    (println (reduce + (map #(-diff-pt2 %) strings)))))
