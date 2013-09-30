(ns cgit.util.zip
  (:require [clojure.string :as str])
  (:import (java.io FileInputStream IOException FileNotFoundException)
           (java.util.zip InflaterInputStream)))

(defn path-from-hash [hash]
  (apply str (concat ".git/objects/" (take 2 hash) "/" (drop 2 hash))))

(defn unzip-blob [hash]
  (let [file (path-from-hash hash)]
    (let [inflater (java.util.zip.InflaterInputStream. (java.io.FileInputStream. file))
          buffer (make-array Byte/TYPE 1024)]
      (.read inflater buffer 0 1024)
      (apply str (map char buffer)))))