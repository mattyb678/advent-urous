(ns advent-of-code.day10)

(def -look-say
  (memoize
   (fn [num]
     (let [str-num (str num)]
       (->> str-num
            (partition-by identity)
            (map #(str (count %) (nth % 0)))
            (apply str)
            )))))

(defn- -apply-times [input times]
  (loop [i 0 in input]
    (if (< i times)
      (recur (inc i) (-look-say in))
      in)))

(defn challenge []
  (println "Day 10")
  (println (count (str (-apply-times 1113222113 40))))
  (println (count (str (-apply-times 1113222113 50)))))
