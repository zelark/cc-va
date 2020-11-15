(ns web-card.card-test
  (:require [clojure.test :refer [deftest testing is]]
            [web-card.credit-card :as cc]))

(deftest test-valid-card?
  (testing "valid cards"
    (is (cc/valid? "1234567812345670"))
    (is (cc/valid? "4539148803436467"))
    (testing "with spaces"
      (is (cc/valid? "4539 1488 0343 6467"))
      (is (cc/valid? "  45391488  03436467  "))))
  (testing "invalid cards"
    (is (not (cc/valid? "8273123273520569")))
    (is (not (cc/valid? "1234567812345678")))
    (testing "all digits"
      (is (not (cc/valid? "4539148 e 03436467"))))
    (testing "card length"
      (is (not (cc/valid? "")))
      (is (not (cc/valid? "1"))))))
