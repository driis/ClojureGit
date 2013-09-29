(ns cgit.util.zip
  (:require [clojure.string :as str]))

(defn path-from-hash [hash]
  (apply str (concat ".git/" (take 2 hash) "/" (drop 2 hash))))

(defn unzip-blob [hash]
  (path-from-hash hash))