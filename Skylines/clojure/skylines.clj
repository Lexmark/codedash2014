(defn rectLeft [rect] (nth rect 0))
(defn rectRight [rect] (nth rect 2))
(defn rectHeight [rect] (nth rect 1))

(defn make-moves [indeces heightMap lastMove]
    (if (or (nil? heightMap) (empty? heightMap))
        '()
        (let [i (first indeces)
              h (first heightMap)]
            (if (not (= h lastMove))
                (cons i (cons h (make-moves (rest indeces) (rest heightMap) h)))
                (make-moves (rest indeces) (rest heightMap) lastMove)))))

(defn skyline [inputs]
    (let [rectangles (map 
                        (fn [s]
                            (map 
                                (fn [ss] (Integer/valueOf ss))
                                (clojure.string/split (subs s 1 (- (.length s) 1)) #",")))
                        (clojure.string/split inputs #" "))
          heightMap 
            (map (fn [i]
                    (let [heights (map (fn [r] (rectHeight r)) (filter (fn [r] (and (>= i (rectLeft r)) (< i (rectRight r)))) rectangles))]
                        (if (or (nil? heights) (empty? heights))
                            0
                            (apply max heights))))
                 (take (+ 1 (apply max (map (fn [r] (rectRight r)) rectangles))) (iterate inc 0)))]
        (clojure.string/join "," (make-moves (take (count heightMap) (iterate inc 0)) heightMap 0))))

(doseq [inputs (repeatedly read-line) :while inputs] (println (skyline inputs)))
