(ns advent-of-code.day6
  (:require [clojure.java.io :as io]))

(def instruction-regex #"(.*)\s(\d+),(\d+).*\b(\d+),(\d+)")

(defn- -parse-action [instruction]
  (case instruction
    "turn on" :turn-on
    "turn off" :turn-off
    "toggle" :toggle
    :unknown))

(defn- -init [x-size y-size]
  (vec (repeat y-size (vec (repeat x-size 0)))))

(defn- -parse-instruction [instruct-str]
  (let [matches (re-matches instruction-regex instruct-str)
        instruct (nth matches 1)
        start-x (read-string (nth matches 2))
        start-y (read-string (nth matches 3))
        end-x (read-string (nth matches 4))
        end-y (read-string (nth matches 5))]
    (if (nil? matches)
      nil
      {:action (-parse-action instruct) :start {:x start-x :y start-y}
       :end {:x end-x :y end-y}})))

(defn- -get-status [action num part]
  (case action
    :turn-on (if (= part :part1) 1 (inc num))
    :turn-off (if (= part :part1)
                0
                (let [new-num (dec num)] (if (< new-num 0) 0 new-num)))
    :toggle (if (= part :part1)
              (if (= num 1) 0 1)
              (+ 2 num))
    ))

(defn- -update-row
  ([row operation start end] (-update-row row operation start end :part1))
  ([row operation start end part]
   (reduce #(assoc %1 %2 (-get-status operation (get %1 %2) part))
           row
           (range start (inc end)))))

(defn- -update-rows
  ([lights operation start-y end-y start-x end-x]
   (-update-rows lights operation start-y end-y start-x end-x :part1))
  ([lights operation start-y end-y start-x end-x part]
   (reduce #(assoc %1 %2 (-update-row (get %1 %2) operation start-x end-x part))
           lights
           (range start-y (inc end-y)))))

(defn- -apply-instruction
  ([instruct light-vec] (-apply-instruction instruct light-vec :part1))
  ([instruct light-vec part]
   (let [action (instruct :action)
         start-x (get-in instruct [:start :x])
         end-x (get-in instruct [:end :x])
         start-y (get-in instruct [:start :y])
         end-y (get-in instruct [:end :y])]
     (-update-rows light-vec action start-y end-y start-x end-x part))))

(defn- -apply-instructions
  ([file-name lights] (-apply-instructions file-name lights :part1))
  ([file-name lights part]
   (with-open [rdr (io/reader (io/file (io/resource file-name)))]
     (loop [instructions (map #(-parse-instruction %) (line-seq rdr))
            light-vec lights]
       (if (empty? instructions)
         light-vec
         (recur (rest instructions)
                (-apply-instruction (first instructions) light-vec part)))))))

(defn challenge []
  (println "Day 6")
  (println "Lights on: " (->> (-apply-instructions "day6.txt" (-init 1000 1000))
                (flatten)
                (filter #(= 1 %))
                (count)))
  (println "Total Brightness: "
           (->> (-apply-instructions "day6.txt" (-init 1000 1000) :part2)
                (flatten)
                (reduce +))))
