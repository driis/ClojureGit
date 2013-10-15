(ns cgit.pack-test
  (:require [clojure.test :refer :all]
            [cgit.pack :refer :all]
            [cgit.io :refer :all]))

(deftest a-index-file-is-valid
  (let [buffer (read-file ".git/objects/pack/pack-b7d376ad24ea19fb93f5429eb2c6f190002f5fce.idx")]
    (is (valid-pack? buffer))))

(deftest a-random-file-is-invalid
  (let [buffer (read-file "readme.md")]
    (is (not (valid-pack? buffer)))))
