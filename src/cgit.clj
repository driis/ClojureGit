(ns cgit
  (:require [cgit.util.zip :as zip]
             [cgit.parse :as parse]))


(defn cat-file
  "Returns the content of a git object with the given hash
  mode can be: t = type, p = text content"
  [mode hash]
  (cond
    (= "t" mode) (:type (parse/get-object hash))
    (= "s" mode) (:length (parse/get-object hash))
    (= "p" mode) (parse/get-content-string (parse/get-object hash))
    (= "e" mode) (if (parse/get-object hash)
                   true
                   false)
    :else (throw (Exception. (str "Unknown type " mode)))))
