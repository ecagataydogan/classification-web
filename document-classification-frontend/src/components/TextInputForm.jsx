import React, { useState } from "react";

function TextInputForm({ onSubmit }) {
  const [title, setTitle] = useState("");
  const [abstract, setAbstract] = useState("");
  const [keywords, setKeywords] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    // Title, Abstract ve Keywords'i tek bir string olarak birleştir
    const combinedText = `Title: ${title}\nAbstract: ${abstract}\nKeywords: ${keywords}`;
    onSubmit(combinedText);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="title">
          <strong>Title:</strong>
        </label>
        <input
          id="title"
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Başlık girin"
        />
      </div>
      <div>
        <label htmlFor="abstract">
          <strong>Abstract:</strong>
        </label>
        <textarea
          id="abstract"
          value={abstract}
          onChange={(e) => setAbstract(e.target.value)}
          placeholder="Özeti buraya girin..."
          rows="4"
          cols="50"
        ></textarea>
      </div>
      <div>
        <label htmlFor="keywords">
          <strong>Keywords:</strong>
        </label>
        <input
          id="keywords"
          type="text"
          value={keywords}
          onChange={(e) => setKeywords(e.target.value)}
          placeholder="Anahtar kelimeleri girin"
        />
      </div>
      <br />
      <button type="submit">Gönder</button>
    </form>
  );
}

export default TextInputForm;
