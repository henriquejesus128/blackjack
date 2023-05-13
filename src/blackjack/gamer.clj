(defn new-card []
  (inc (rand-int 13)))

(defn player [player-name]
  (def card-1 (new-card))
  (def card-2 (new-card))
  {:user player-name
   :cards [card-1, card-2]})

(println (player "Henrique"))