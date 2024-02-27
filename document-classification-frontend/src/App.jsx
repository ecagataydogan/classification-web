// src/App.js
import React, { useState } from "react";
import TextInputForm from "./components/TextInputForm";
import ImageGallery from "./components/ImageGallery";
import "./App.css";

function App() {
  const [response, setResponse] = useState("");
  const [imagesToShow, setImagesToShow] = useState([]); // Gösterilecek resimlerin listesini tutacak durum
  const [errorMessage, setErrorMessage] = useState(""); // Hata mesajı durumu

  const submitText = async (text) => {
    const response = await fetch("http://localhost:8080/classify", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ text }),
    });

    const data = await response.json();
    setResponse(JSON.stringify(data, null, 2));

    if (!data.labels) {
      // labels anahtarını kontrol et
      setErrorMessage(data.error);
      return; // labels yoksa, fonksiyondan çık
    }

    // Yanıttaki etiketleri döngüye al ve uygun resimleri belirle
    const images = data.labels
      .filter((label) => label.startsWith("SDG"))
      .map((label) => `${label.replace("SDG", "")}.png`); // "SDG1" -> "1.png", "SDG2" -> "2.png", vb.

    setImagesToShow(images); // Resim listesini güncelle
  };

  return (
    <div className="App">
      <h1>Document Classifier for Sustainable Development Goals</h1>
      <TextInputForm onSubmit={submitText} />
      {errorMessage ? (
        <p>{errorMessage}</p>
      ) : (
        <ImageGallery images={imagesToShow} />
      )}
    </div>
  );
}

export default App;
