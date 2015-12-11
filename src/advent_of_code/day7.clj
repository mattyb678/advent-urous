(ns advent-of-code.day7
  (:require [clojure.java.io :as io]))

(defn- -parse-instruction [instruct-str]
  (let [regex #"(?:(NOT)\s(.*)|(.*)\s(AND|OR|LSHIFT|RSHIFT)\s(.*)|(.*))\s(->)\s(.*)"]
    (->> (re-find regex instruct-str)
         (rest)
         (filter (comp not nil?)))))

(defn- -parse-instructions [instructions]
  (reduce
   #(let [[a b c d e] (-parse-instruction %2)]
      (cond
       (= b "->") (assoc %1 c {:val a})
       (= a "NOT") (assoc %1 d {:op bit-not :operand1 b})
       (= b "AND") (assoc %1 e {:op bit-and :operand1 a :operand2 c})
       (= b "OR") (assoc %1 e {:op bit-or :operand1 a :operand2 c})
       (= b "LSHIFT") (assoc %1 e {:op bit-shift-left :operand1 a :operand2 c})
       (= b "RSHIFT") (assoc %1 e {:op unsigned-bit-shift-right :operand1 a :operand2 c})))
   {}
   instructions))

(declare -eval-var)

(def -get-val
  (memoize
   (fn [ctx val]
     (let [value (read-string val)]
       (if (number? value)
         value
         (-eval-var ctx val))))))

(defn- -calc-var [ctx k]
  (let [{:keys [op val operand1 operand2]} (get ctx k)]
    (if (nil? op)
      (-get-val ctx val)
      (if (nil? operand2)
        (op (-get-val ctx operand1))
        (op (-get-val ctx operand1) (-get-val ctx operand2))))))

(defn- -eval-var [ctx k]
  (bit-and 0xffff (-calc-var ctx k)))

(defn- -get-instructions [file-name]
  (with-open [rdr (io/reader (io/file (io/resource file-name)))]
    (doall (-parse-instructions (line-seq rdr)))))

(defn challenge []
  (let [ctx (-get-instructions "day7.txt")
        val-a (-eval-var ctx "a")]
    (println "Day 7")
    (println "a = " val-a)
    (println "a (part2) = " (-eval-var (assoc ctx "b" {:val (str val-a)}) "a"))))
