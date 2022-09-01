import './App.css'
import React from 'react'
import {BrowserRouter, Route, Routes, Navigate, useParams} from 'react-router-dom'
import CurrentHealthPage from './pages/CurrentHealthPage'
import HealthHistoryPage from './pages/HealthHistoryPage'
import Cookies from 'js-cookie'

export default function App() {
    // const [authenticated, setAuthenticated] = React.useState((Cookies.get('authenticated') === 'true'))

    const PageParamsWrapper = (props) => {
        const pageParams = useParams();
        return <props.element {...{...props, pageParams: pageParams} } />
    }

    // function HomePage() {
    //     return <CurrentHealthPage />
    //
    // }

    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<CurrentHealthPage />} />
                <Route path='/:name' element={<PageParamsWrapper element={HealthHistoryPage} />} />
            </Routes>
        </BrowserRouter>
    )
}
