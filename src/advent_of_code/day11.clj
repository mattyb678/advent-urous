(ns advent-of-code.day11)

(defn- -inc-char [c]
  (char (inc (byte c))))

(defn- -inc-password [pass]
  (apply str
         (loop [next-pass pass acc []]
           (cond
            (= (last next-pass) \z) (recur (drop-last next-pass) (concat acc [\a]))
            (not (empty? next-pass)) (concat (drop-last next-pass)
                                             [(-inc-char (last next-pass))]
                                             acc)
            :else ""))))

(defn- -has-straight [s]
  (->> s
       (map byte)
       (partition 3 1)
       (map (fn [[a b c]] (and (= 1 (- c b)) (= 1 (- b a)))))
       (reduce #(or %1 %2) false)))

(def invalid-chars [\i \o \l])

(defn- -contains-chars [s chars]
  (not (= nil (re-find (re-pattern (str "[" (apply str chars) "]")) s))))

(defn- -has-doubles [s]
  (let [regex #"(.)\1.*(.)\2"]
    (not (= nil (re-find regex s)))))

(defn- -next-password [s]
  (loop [next-pass s]
    (if (and (-has-straight next-pass)
             (-has-doubles next-pass)
             (not (-contains-chars next-pass invalid-chars)))
      next-pass
      (recur (-inc-password next-pass)))))

(defn challenge []
  (let [next-pass (-next-password "vzbxkghb")]
    (println "Day 11")
    (println next-pass)
    (println (-next-password (-inc-password next-pass)))))
