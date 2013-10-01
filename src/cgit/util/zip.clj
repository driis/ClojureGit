(ns cgit.util.zip
  (:require [clojure.string :as str])
  (:import (java.io FileInputStream IOException FileNotFoundException)
           (java.util.zip InflaterInputStream)))

(def buffer-size 1024)
(defn path-from-hash [hash]
  (apply str (concat ".git/objects/" (take 2 hash) "/" (drop 2 hash))))

(defn unzip-blob [hash]
  (let [file (path-from-hash hash)]
    (let [inflater (java.util.zip.InflaterInputStream. (java.io.FileInputStream. file))]
      (loop [buffer (make-array Byte/TYPE buffer-size)
             res []]
        (let [read (.read inflater buffer 0 buffer-size)]
          (if (< read 1)
            res
            (recur buffer (concat res (doall (take read buffer)))))))))
)
