(ns web-card.credit-card
  (:require [clojure.string :as str]))

(defn valid?
  "Validate credit card number using Luhn algorithm.
   `card-number` should be a string of digits.
   Whitespaces are allowed but ingnored."
  [card-number]
  (let [card-number' (str/replace card-number #"\s" "")]
    (and (and (every? #(Character/isDigit %) card-number')
              (> (count card-number') 1))
        (let [digits  (map #(Character/digit % 10) card-number')
              factors (cycle [1 2])
              sum     (->> (reverse digits)
                           (map * factors)
                           (map #(cond-> % (> % 9) (- 9)))
                           (apply +))]
          (zero? (rem sum 10))))))
