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
  "Reads a single unsigned integer from buffer sequence of bytes."
  (reduce #(bit-or (bit-shift-left %1 8) (bit-and 0xFF %2)) 0 (take 4 buffer)))

(defn read-uints [buffer]
  "Reads a lazy sequence of unsigned integers from a buffer sequence of bytes."
  (let [[next rest] (split-at 4 buffer)]
    (if (< (count next) 4)
      []
      (cons (read-uint next) (lazy-seq (read-uints rest))))))