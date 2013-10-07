(ns cgit
  (:require [cgit.util.zip :as zip]
             [cgit.parse :as parse]))

(defn- log-seq [hash]
  (let [commit (parse/get-commit hash)]
    (if (:parent commit)
      (cons commit (lazy-seq (log-seq (:parent commit))))
      [commit])))

(defn get-head []
  nil) ;todo

(defn cat-file
  "Returns the content of a git object with the given hash
  mode can be: t = type, p = text content"
  [mode hash]
  (cond
    (= "t" mode) (:type (parse/get-blob hash))
    (= "s" mode) (:length (parse/get-blob hash))
    (= "p" mode) (parse/get-content-string (parse/get-blob hash))
    (= "e" mode) (if (parse/get-blob hash)
                   true
                   false)
    :else (throw (Exception. (str "Unknown type " mode)))))

(defn log
  "Returns the git log as a lazy sequence"
  [commit options]
    (let [commits (log-seq commit)
          max (:max options)]
      (if max
        (take max commits)
        commits)))