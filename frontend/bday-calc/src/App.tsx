import { useEffect, useState } from 'react'
import './App.css'

function App() {

  interface Response {
    diff: number;
    untilBday: number;
  }

  const url = "localhost";
  const [inputValue, setInputValue] = useState('');
  const [bday, setBday] = useState('');
  const [diff, setDiff] = useState(0);
  const [untilBday, setUntilBday] = useState(0);


 
    const getBday = async (bday: string) => {
      try {
        const response = await fetch(`http://${url}:9090/bday?bday=${bday}`);
        if(!response.ok) throw new Error("There was a problem with the fetching");
        const data: Response = await response.json();

        setDiff(data.diff);
        setUntilBday(data.untilBday)
      } catch (error) {
        console.error(error);
      }
    }



  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!inputValue) return;
    setBday(inputValue);
    getBday(inputValue);
  }

  return (
    <>
      <h1 className='font-bold'>Birthday Calculator</h1>
      <form onSubmit={handleSubmit}>
        <label>What day you where born?</label>
        <input 
          type="date" 
          className='input'
          value={inputValue}
          onChange={(e) => setInputValue(e.target.value)}
          />
        <button className='btn btn-primary'>Accept</button>
      </form>
      <div>
        <h2>You were burn {diff} days ago</h2>
        <h2>Your birthday is in {untilBday} days</h2>
      </div>
    </>
  )
}

export default App
