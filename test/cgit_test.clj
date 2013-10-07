(ns cgit-test
  (:require [clojure.test :refer :all]
            [cgit :refer :all]
            [cgit.parse :refer :all]))

(deftest cat-file-can-get-type
  (is (= "blob" (cat-file "t" "8c8975696e41a08644f3816d121c98a9249f2d59"))))

(deftest cat-file-can-get-size
  (is (= 39 (cat-file "s" "8c8975696e41a08644f3816d121c98a9249f2d59"))))

(deftest cat-file-can-validate
  (is (true? (cat-file "e" "8c8975696e41a08644f3816d121c98a9249f2d59"))))

(deftest cat-file-can-get-content
  (is (= 39 (count (cat-file "p" "8c8975696e41a08644f3816d121c98a9249f2d59")))))

(deftest log-can-get-a-single-commit
  (let [log-stream (log "8d9d6d3ade568b8f10a8540447b89b77a3097cba" {})]
    (let [commit (first log-stream)]
      (is commit)
      (is (= "Refactor parse-author into smaller parts of composition." (:comment commit))))))

(deftest log-can-iterate-three-commits
  (let [log-stream (log "8d9d6d3ade568b8f10a8540447b89b77a3097cba" {})]
    (doseq [commit (take 3 log-stream)]
      (is commit)
      (is (= "Dennis Riis" (:name (:author commit)))))))

(deftest log-can-take-max-option
  (let [log-stream (log "8d9d6d3ade568b8f10a8540447b89b77a3097cba" {:max 2})]
    (is (= 2 (count log-stream)))))

