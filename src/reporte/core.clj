(ns reporte.core
  (:require [hiccup.page :refer [html5]]
            [pdfkit-clj.core :refer [as-stream gen-pdf]]
            [compojure.core :refer [defroutes routes GET]]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]])
  (:gen-class))

(defn build-html []
  (html5
    [:table
     [:thead
      [:tr
       [:th "Nombre"]
       [:th "Email"]
       ]
      ]
     [:tbody
      [:tr
       [:td "Hector"]
       [:td "hectorqlucero@gmail.co"]
       ]
      [:tr
       [:td "Pedro"]
       [:td "ppacas@gmail.co"]
       ]
      [:tr
       [:td "Maria"]
       [:td "mpacas@gmail.com"]]
      ]]))

(defn build-pdf
  []
  (let [html (build-html)
        filename "pdfreporte.pdf"]
    {:headers {"Content-Type" "application/pdf"
               "Content-Disposition" (str "attachment;filename=" filename)
               "Cashe-Control" "no-cache;no-store;max-age=0,must-revalidate,pre-check=0,post-check=0"}
     :body (as-stream (gen-pdf html
                               :margin {:top 20 :right 15 :bottom 50 :left 15}))}))

(defroutes app-routes
  (route/resources "/")
  (GET "/" [] (build-html))
  (GET "/pdf" [] (build-pdf))
  (route/not-found "Not Found"))

(def app
  (-> (routes #'app-routes)
      (wrap-defaults site-defaults)
      (wrap-reload)))

(defn -main
  []
  (jetty/run-jetty app {:port 3000}))

(comment
  (build-pdf)
  (build-html))
