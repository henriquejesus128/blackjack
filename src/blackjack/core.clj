(ns blackjack.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn new-card []
  (inc (rand-int 13)))

(defn player [player-name]
  (let [card-1 (new-card)
        card-2 (new-card)]
  {:player-name player-name
   :cards [card-1, card-2]}))

(println (player "Henrique"))
(println (player "Dealer"))