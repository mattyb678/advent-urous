(ns advent-of-code.core
  (:require [advent-of-code.day1 :as day1]))

(defn -main
  [& args]
  (if (= (count args) 1)
    (let [namespace (symbol (str "advent-of-code." (first args)))]
      (do
        (require namespace)
        (let [day (find-ns namespace)]
          ((ns-resolve day 'challenge)))))
    (println "Specify the day that you want to run, e.g. 'day1'")))
