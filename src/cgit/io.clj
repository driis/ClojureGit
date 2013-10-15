(ns cgit.io
  (:require [clojure.java.io :as cio]))

(def buffer-size 1024)

(defn path-from-hash [hash]
  (apply str (concat ".git/objects/" (take 2 hash) "/" (drop 2 hash))))

(defn read-full-stream [stream]
  (loop [buffer (make-array Byte/TYPE buffer-size)
         res []]
    (let [read (.read stream buffer 0 buffer-size)]
      (if (< read 1)
        res
        (recur buffer (concat res (doall (take read buffer))))))))

(defn read-file [file]
  (let [input (cio/input-stream file)]
    (let [result (read-full-stream input)]
      (.close input)
      result)))

(defn read-uint [buffer]
  (reduce #(bit-or (bit-shift-left %1 8) (bit-and 0xFF %2)) 0 (take 4 buffer)))