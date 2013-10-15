(ns cgit.io)

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
