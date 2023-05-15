(ns blackjack.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn new-card []
  (inc (rand-int 13)))

(defn JQK->10 [card]
  (if (> card 10) 10 card))

(defn A->11 [card]
  (if (= card 1) 11 card))

(defn points-cards [cards]
  (let [cards-JQK (map JQK->10 cards)
        card-A11 (map A->11 cards-JQK)
        points-A1 (reduce + cards-JQK)
        points-A11 (reduce + card-A11)]
    (if (> points-A11 21) points-A1 points-A11)))

(defn player [player-name]
  (let [card-1 (new-card)
        card-2 (new-card)
        cards [card-1 card-2]
        points (points-cards cards)]
    {:player-name player-name
     :cards cards
     :points points}))

(defn get-card [player]
  (let [card (new-card)
        cards (conj (:cards player) card)
        new-player (update player :cards conj card)
        points (points-cards cards)]
    (assoc new-player :points points)))

(defn player-decision [player]
  (= (read-line) "sim"))

(defn dealer-decision [player-points dealer]
  (let [dealer-points (:points dealer)]
    (<= dealer-points player-points)))

(defn game [player fn-decision]
  (println (:player-name player) "quer mais carta ?")
  (if (fn-decision player)
    (let [player-get-card (get-card player)]
      (println player-get-card)
      (recur player-get-card fn-decision))
    player))

(def player-1 (player "Henrique"))
(println player-1)

(def dealer (player "Dealer"))
(println dealer)

(def player-after-game (game player-1 player-decision))
(game dealer (partial dealer-decision (:points player-after-game)))

(defn end-game [player dealer]
  (let [player-points (:points player)
        dealer-points (:points dealer)
        player-name (::player-name player)
        dealer-name (::player-name dealer)
        message (cond
                  (and (> player-points 21) (> dealer-points 21)) "Ambos perderam!"
                  (= player-points delaer-points) "Empatou!"
                  (> player-points 21) (str dealer-points "Ganhou!")
                  (> dealer-points 21) (str player-points "Ganhou!")
                  (> player-points dealer-points) (str player-points "Ganhou!")
                  (> dealer-points player-points) (str dealer-points "Ganhou!"))]
    (println player)
    (println dealer)
    (println message)))